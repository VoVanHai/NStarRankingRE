package my.graph;

import java.util.ArrayList;
import java.util.Iterator;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;

public class TypedVerTex {
	private String id;
	private String type;
	
	private ArrayList<Object>lstAttributes;
	
	/*public TypedVerTex(String type,Example ex, Attributes atts) {
		//get id
		Attribute attId = atts.getId();
		if(attId!=null)
			this.id=ex.getValueAsString(attId);

		this.type=type;
		
		//get values
		lstAttributes=new ArrayList<Object>();
		Iterator<Attribute> it = atts.iterator();
		while(it.hasNext()){
			Attribute att=it.next();
			String val=ex.getValueAsString(att);
			lstAttributes.add(val);
		}
	}*/
	public TypedVerTex(String type,Example ex) {
		Attributes atts=ex.getAttributes();
		//get id
		Attribute attId = atts.getId();
		if(attId!=null)
			this.id=ex.getValueAsString(attId);

		this.type=type;
		
		//get values
		lstAttributes=new ArrayList<Object>();
		Iterator<Attribute> it = atts.iterator();
		while(it.hasNext()){
			Attribute att=it.next();
			String val=ex.getValueAsString(att);
			lstAttributes.add(val);
		}
	}
	

	public String getType() {
		return type;
	}



	public String getId() {
		return id;
	}
	
	public ArrayList<Object> getLstAttributes() {
		return lstAttributes;
	}

	public void setLstAttributes(ArrayList<Object> lstAttributes) {
		this.lstAttributes = lstAttributes;
	}
	
	@Override
	public String toString() {
		return "TypedVerTex [id=" + id + ", type=" + type + "]";
	}
	
}
