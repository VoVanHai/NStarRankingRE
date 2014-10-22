package hcm.org.nstar.model;


public class Keyword{
	private long idKeyword;
	private String keyword;
	private String stemmingVariations;
	private String url;
	public Keyword() {
		// TODO Auto-generated constructor stub
	}
	public Keyword(long idKeyword, String keyword, String stemmingVariations,
			String url) {
		this.idKeyword = idKeyword;
		this.keyword = keyword;
		this.stemmingVariations = stemmingVariations;
		this.url = url;
	}
	public long getIdKeyword() {
		return idKeyword;
	}
	public void setIdKeyword(long idKeyword) {
		this.idKeyword = idKeyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStemmingVariations() {
		return stemmingVariations;
	}
	public void setStemmingVariations(String stemmingVariations) {
		this.stemmingVariations = stemmingVariations;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idKeyword ^ (idKeyword >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Keyword other = (Keyword) obj;
		if (idKeyword != other.idKeyword)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return keyword;
	}
}
