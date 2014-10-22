package hcm.org.nstar.model;


public class Publication{
	private long idPaper;
	private String doi;
	private String isbn;
	private String url;
	private String title;
	private String abstractText;
	private String volumn;
	private int pages;
	private int year;
	private String viewPublication;
	private String bibTex;
	private String endNote;
	private String idJournal;
	private String idConference;
	private String idMagazine;
	private String version;
	private String paperFile;

	public Publication() {
		// TODO Auto-generated constructor stub
	}

	public Publication(long idPaper, String doi, String isbn, String url,
			String title, String abstractText, String volumn, int pages,
			int year, String viewPublication, String bibTex, String endNote,
			String idJournal, String idConference, String idMagazine,
			String version, String paperFile) {
		super();
		this.idPaper = idPaper;
		this.doi = doi;
		this.isbn = isbn;
		this.url = url;
		this.title = title;
		this.abstractText = abstractText;
		this.volumn = volumn;
		this.pages = pages;
		this.year = year;
		this.viewPublication = viewPublication;
		this.bibTex = bibTex;
		this.endNote = endNote;
		this.idJournal = idJournal;
		this.idConference = idConference;
		this.idMagazine = idMagazine;
		this.version = version;
		this.paperFile = paperFile;
	}

	public long getIdPaper() {
		return idPaper;
	}

	public void setIdPaper(long idPaper) {
		this.idPaper = idPaper;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getViewPublication() {
		return viewPublication;
	}

	public void setViewPublication(String viewPublication) {
		this.viewPublication = viewPublication;
	}

	public String getBibTex() {
		return bibTex;
	}

	public void setBibTex(String bibTex) {
		this.bibTex = bibTex;
	}

	public String getEndNote() {
		return endNote;
	}

	public void setEndNote(String endNote) {
		this.endNote = endNote;
	}

	public String getIdJournal() {
		return idJournal;
	}

	public void setIdJournal(String idJournal) {
		this.idJournal = idJournal;
	}

	public String getIdConference() {
		return idConference;
	}

	public void setIdConference(String idConference) {
		this.idConference = idConference;
	}

	public String getIdMagazine() {
		return idMagazine;
	}

	public void setIdMagazine(String idMagazine) {
		this.idMagazine = idMagazine;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPaperFile() {
		return paperFile;
	}

	public void setPaperFile(String paperFile) {
		this.paperFile = paperFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPaper ^ (idPaper >>> 32));
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
		Publication other = (Publication) obj;
		if (idPaper != other.idPaper)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return title;
	}
}
