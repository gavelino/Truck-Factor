package aserg.gtf.model.newstudy;

import aserg.gtf.model.AbstractEntity;
import aserg.gtf.model.DeveloperInfo;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TFDeveloper
  extends AbstractEntity
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  protected Long id;
  private String name;
  private String email;
  private String username;
  private String lastCommitSha;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastCommitDate;
  private String firstCommitSha;
  @Temporal(TemporalType.TIMESTAMP)
  private Date firstCommitDate;
  private int nCommits;
  private int GitHubId;
//  @OneToMany(cascade={javax.persistence.CascadeType.REFRESH})
//  private List<LogCommitInfo> commits;
  
  public TFDeveloper() {}
  
  public TFDeveloper(DeveloperInfo devInfo)
  {
    this.name = devInfo.getName();
    this.email = devInfo.getEmail().toLowerCase();
    this.username = devInfo.getUserName();
    this.firstCommitDate = devInfo.getFirstCommit().getMainCommitDate();
    this.firstCommitSha = devInfo.getFirstCommit().getSha();
    this.lastCommitDate = devInfo.getLastCommit().getMainCommitDate();
    this.lastCommitSha = devInfo.getLastCommit().getSha();
    this.nCommits = devInfo.getCommits().size();
    this.GitHubId = devInfo.getUserId()>=0? devInfo.getUserId() : -1;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getLastCommitSha()
  {
    return this.lastCommitSha;
  }
  
  public void setLastCommitSha(String lastCommitSha)
  {
    this.lastCommitSha = lastCommitSha;
  }
  
  public Date getLastCommitDate()
  {
    return this.lastCommitDate;
  }
  
  public void setLastCommitDate(Date lastCommitDate)
  {
    this.lastCommitDate = lastCommitDate;
  }
  
  public String getFirstCommitSha()
  {
    return this.firstCommitSha;
  }
  
  public void setFirstCommitSha(String firstCommitSha)
  {
    this.firstCommitSha = firstCommitSha;
  }
  
  public Date getFirstCommitDate()
  {
    return this.firstCommitDate;
  }
  
  public void setFirstCommitDate(Date firstCommitDate)
  {
    this.firstCommitDate = firstCommitDate;
  }
  
  public int getnCommits()
  {
    return this.nCommits;
  }
  
  public void setnCommits(int nCommits)
  {
    this.nCommits = nCommits;
  }

public int getGitHubId() {
	return GitHubId;
}

public void setGitHubId(int gitHubId) {
	GitHubId = gitHubId;
}
  
}
