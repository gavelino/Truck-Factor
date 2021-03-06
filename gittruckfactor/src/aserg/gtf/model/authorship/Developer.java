package aserg.gtf.model.authorship;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	private String name;
	private String email;
//	private String newUserName;
	private boolean removed;
	private Integer authorId;
	
	@Enumerated(EnumType.STRING)
	private DevStatus status;
	
	@OneToMany(cascade = { CascadeType.REFRESH })
	private List<Developer> origemDevelopers;
	
	@OneToMany(cascade = { CascadeType.REFRESH })
	private List<AuthorshipInfo> authorshipInfos = new ArrayList<AuthorshipInfo>();
	private List<File> authorshipFiles;
	
	public Developer() {
		// TODO Auto-generated constructor stub
	}
	public Developer(String name, String email, Integer authorId) {
		super();
		this.name = name;
		this.email = email;
//		this.newUserName = userName;
		this.removed = false;
		this.status = DevStatus.DEFAULT;
		this.authorId = authorId;
	}
	
	public Developer(Integer authorId) {
		super();
//		this.newUserName = userName;
		this.authorId = authorId;
		this.removed=false;
	}
//	public static String createUserName(String name, String email){
//		String userName;
//		if (!email.isEmpty())
//			userName = email;	
//		else{
//			userName = "[NoEmail]"+name;
//			if (name.isEmpty())
//				userName = "[NoEmail-NoName]";	
//		}
//		return userName;
//	}
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AuthorshipInfo> getAuthorshipInfos() {
		return authorshipInfos;
	}

	public void setAuthorshipInfos(List<AuthorshipInfo> authorshipInfos) {
		this.authorshipInfos = authorshipInfos;
	}

	public void addAuthorshipInfo(AuthorshipInfo authorshipInfo) {
		this.authorshipInfos.add(authorshipInfo);		
	}

	@Override
	public String toString() {
		return name + " ("+ authorId+ ")";
	}

//	public String getNewUserName() {
//		return newUserName;
//	}

//	public void setNewUserName(String newUserName) {
//		this.newUserName = newUserName;
//	}

	public boolean isRemoved() {
		return removed;
	}

	public void setAsRemoved() {
		this.removed = true;
		this.status = DevStatus.REMOVED;
	}

	public DevStatus getStatus() {
		return status;
	}
	public void setStatus(DevStatus status) {
		this.status = status;
	}
	public List<Developer> getOrigemDevelopers() {
		return origemDevelopers;
	}
	public void addOrigemDeveloper(Developer dev) {
		if(origemDevelopers == null)
			origemDevelopers = new ArrayList<Developer>();
		origemDevelopers.add(dev);
	}
	public void setOrigemDevelopers(List<Developer> origemDevelopers) {
		this.origemDevelopers = origemDevelopers;
	}
	
	public List<File> getAuthorshipFiles() {
		if (authorshipFiles == null){
			authorshipFiles = new ArrayList<File>();
			for (AuthorshipInfo authorship : this.getAuthorshipInfos()) {
				if (authorship.isDOAAuthor())
					authorshipFiles.add(authorship.getFile());
			}	
		}			
		return authorshipFiles;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public int getDevChanges(){
		int count = 0;
		for (AuthorshipInfo authorshipInfo : authorshipInfos) {
			count+=(authorshipInfo.isFirstAuthor()||authorshipInfo.isSecondaryAuthor()?1:0)+authorshipInfo.getnDeliveries();
		}
		return count;
	}
}