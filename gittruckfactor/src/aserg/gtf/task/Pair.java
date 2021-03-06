package aserg.gtf.task;

import java.util.HashSet;
import java.util.Set;

public class Pair {
	private String name;
	private String email;
//	private String username;  
	private String shaExample; 
	private NewAlias alias; 
	private int gitHubId=0;
	
	public Pair(String name, String email, String shaExample) {
		super();
		this.name = name;
		this.email = email;
		this.shaExample = shaExample;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPairString(){
		return name.toUpperCase()+"**"+email.toUpperCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		Pair other = (Pair)obj;
		return this.getPairString().equals(other.getPairString());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return getPairString().hashCode();
	}
	
	public int getUserId() {
		if (alias == null)
			return -1;
		return alias.getAliasID();
	}
	public String getShaExample() {
		return shaExample;
	}
	@Override
	public String toString() {
		return name+"**"+email + "(" + getUserId()+ " - "+ gitHubId + ")";
	}
	public NewAlias getAlias() {
		return alias;
	}
	public void setAlias(NewAlias alias) {
		this.alias = alias;
	}
	public int getGitHubId() {
		return gitHubId;
	}
	public void setGitHubId(int gitHubId) {
		this.gitHubId = gitHubId;
	}
	
	public Set<NewAlias> getAllAlias(Set<NewAlias> tempSet){
		if (tempSet==null)
			tempSet = new HashSet<NewAlias>();
		
		if (this.alias!=null){
			if (tempSet.contains(this.getAlias()))
				return tempSet;
			else{
				tempSet.add(this.alias);
				for (Pair pair : this.alias.getPairs()) {
					tempSet.addAll(pair.getAllAlias(tempSet));
				}
			}
		}
		return tempSet;
	}
}
