package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.example.table.DoubleArrayDataRow;
import com.rapidminer.example.table.MemoryExampleTable;
import com.rapidminer.tools.Ontology;

public class GenerateSampleData {

	public ExampleSet createExampleSet(Object[][]data, String sourceName){
		ExampleSet es=ExampleSetFactory.createExampleSet(data);
		es.setSource(sourceName.toLowerCase());
		return es;
	}
	/**
	 * 
	 * @param data
	 * @param sourceName
	 * @param idIndex: index of column id
	 * @return
	 */
	public ExampleSet createExampleSet(Object[][]data, String sourceName,int idColIndex){
		// create attributes
		int numberOfAttributes = data[0].length;
		boolean[] nominal = new boolean[numberOfAttributes];
		List<Attribute> attributeList = new ArrayList<Attribute>(numberOfAttributes);
		for (int a = 0; a < numberOfAttributes; a++) {
			Object current = data[0][a];
			Attribute att=null;
			if (current instanceof Number) {
				att=AttributeFactory.createAttribute("att" + (a+1), Ontology.NUMERICAL);
				attributeList.add(att);
				nominal[a] = false;
			} else if (current instanceof String) {
				attributeList.add(att=AttributeFactory.createAttribute("att" + (a+1), Ontology.NOMINAL));
				nominal[a] = true;
			} else {
				throw new RuntimeException("ExampleSetFactory.createExampleSet(Object[][], Object[]): only objects of type String or Number (Double, Integer) are allowed for the object data matrix.");
			}
		}
		// create table
		MemoryExampleTable table = new MemoryExampleTable(attributeList);
		for (int e = 0; e < data.length; e++) {
			double[] dataRow = new double[numberOfAttributes];
			for (int a = 0; a < numberOfAttributes; a++) {
				Object current = data[e][a];
				if (current instanceof Number) {
					if (nominal[a])
						throw new RuntimeException("ExampleSetFactory.createExampleSet(Object[][], Object[]): type of objects did change in column. Only the same type of objects is allowed for complete columns.");
					dataRow[a] = ((Number)current).doubleValue();
				} else if (current instanceof String) {
					if (!nominal[a])
						throw new RuntimeException("ExampleSetFactory.createExampleSet(Object[][], Object[]): type of objects did change in column. Only the same type of objects is allowed for complete columns.");
					dataRow[a] = attributeList.get(a).getMapping().mapString((String)current);
				} else {
					throw new RuntimeException("ExampleSetFactory.createExampleSet(Object[][], Object[]): only objects of type String or Number (Double, Integer) are allowed for the object data matrix.");
				}
			}
			table.addDataRow(new DoubleArrayDataRow(dataRow));
		}
		
		Map<Attribute, String> roles = new HashMap<Attribute, String>();
		roles.put(attributeList.get(idColIndex), Attributes.ID_NAME);
		
		ExampleSet es = table.createExampleSet(roles);
		es.setSource(sourceName.toLowerCase());
		return es;
	}


	public ExampleSet[] readSampleData(){
		Object[][] dataPaper={{1},{2},{3}};
		Object[][] dataAuthor={{1},{2},{3}};
		Object[][] dataCit={{1},{2},{3}};
		Object[][] dataConf={{1},{2},{3}};

		Object[][] dataAuthor_Paper={{1,1},{1,2},{1,3},{2,1},{3,2},{3,3}};
		Object[][] dataConf_Paper={{1,2},{1,1},{2,3}};
		Object[][] dataCit_Paper={{1,3},{2,3}};
		Object[][] dataPaper_Cit={{1,1},{1,3},{2,3}};

		Object[][] dataParams={{"4","4",
		"{<author-paper>;<citation-paper>;<Conference-Paper>;<Paper-Citation>}"}};

		ExampleSet esPaper=createExampleSet(dataPaper,"paper",0);
		ExampleSet esAuthor=createExampleSet(dataAuthor,"author",0);
		ExampleSet esCit=createExampleSet(dataCit,"citation",0);
		ExampleSet esConf=createExampleSet(dataConf,"conference",0);


		ExampleSet esAuthor_Paper=createExampleSet(dataAuthor_Paper,"Author_Paper");
		ExampleSet esConf_Paper=createExampleSet(dataConf_Paper,"Conference_Paper");
		ExampleSet esCit_Paper=createExampleSet(dataCit_Paper,"Citation_Paper");
		ExampleSet esPaper_Cit=createExampleSet(dataPaper_Cit,"Paper_Citation");

		/*Object[] label={"NoTypes","NoRelations","RelationDetails"};
		ExampleSet esParams=ExampleSetFactory.createExampleSet(dataParams,label);*/
		ExampleSet esParams=createExampleSet(dataParams,"params");
		ExampleSet[]eset={
				esParams,
				esPaper,
				esAuthor,
				esCit,
				esConf,
				esAuthor_Paper,
				esConf_Paper,
				esCit_Paper,
				esPaper_Cit
		};

		return eset;
	}

	public void printDatasources(){
		ExampleSet[]eset=readSampleData();
		for(ExampleSet es:eset){
			System.out.println("name: "+es.getSource());
			Iterator<Example> itor = es.iterator();
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				Iterator<Attribute> it = atts.iterator();
				Attribute attID = atts.getId();
				if(attID!=null)
					System.out.print(ex.getValueAsString(attID)+" #id");
				while(it.hasNext()){
					Attribute att=it.next();
					String val=ex.getValueAsString(att);
					System.out.print(val+"\t");
				}
				System.out.println();
			}
			System.out.println("---------------------------------");
		}
	}

	public static void main(String[] args) throws Exception{
		GenerateSampleData bg=new GenerateSampleData();
		bg.printDatasources();
	}
}
