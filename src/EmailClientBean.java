/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc9959;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ejb.*;
import javax.mail.Message;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
import bc9959.Email;
import javax.mail.Flags.Flag;

/**
 *
 * @author NAVNEET
 */
//@Named(value = "emailClientBean")
//@Dependent
@ManagedBean
@SessionScoped

public class EmailClientBean {

    /**
     * Creates a new instance of EmailClientBean
     */
	private String userid="";
	private String password="";
	private ArrayList<DataBean> mails;
	private DataBean c;
	private String touser;
	private String tosubject;
	private String tocontent;
	public DataBean getC(){return c;}
	public void setC(DataBean c){this.c=c;}
		
	@EJB
	private Email email;
        
        public EmailClientBean()
	{
		userid=null;
		password=null;
		mails = new ArrayList<DataBean>();
		c = new DataBean();
		touser=null;
		tosubject=null;
		tocontent=null;	
	}
	
        /*public EmailClientBean(String userid, String password){
            
        this.userid = userid;
        this.password = password;
        }*/
        
	public String getUserid()
	{return userid;}

	public void  setUserid(String userid)
	{this.userid=userid;}

	public String getPassword()
	{return password;}
	
	public void setPassword(String password)
	{this.password=password;}

	public ArrayList<DataBean> getMails()
	{return mails;}
	
	public String getTouser()
	{return touser;}

	public void setTouser(String touser)
	{this.touser=touser;}

	public String getTosubject()
        {return tosubject;}

        public void setTosubject(String tosubject)
        {this.tosubject=tosubject;}

  	public String getTocontent()
        {return tocontent;}

        public void setTocontent(String tocontent)
        {this.tocontent=tocontent;}



	

	public String validateInput()
	{
		if ((userid != null) && (password!=null) && 
	((userid.equals("csuhduke") || userid.equals("csuhduke1") || userid.equals("csuhduke2") || userid.equals("csuhduke3") || userid.equals("csuhduke4") || userid.equals("csuhduke5") || userid.equals("csuhduke6") || userid.equals("csuhduke7") || userid.equals("csuhduke8") || userid.equals("csuhduke9") ) && 
(password.equals("cs6522"))))
	{
	return "welcome.xhtml";
	}
	else
	{
	return "failure";
	} 
}

public String checkMail()
{
mails.clear();
int nMails = email.connect(userid,password);
System.out.println("the no of messages:"+nMails);
System.out.println("userid is:"+userid);
for(int i = 0 ; i <= nMails ; i++) 
{
	DataBean singlemail = new DataBean();
	singlemail.setB(false);
        singlemail.setFrom(email.getFromAddress(i));
	System.out.println("mail's from address:"+singlemail.getFrom());
	singlemail.setSubject(email.getSub(i));
	singlemail.setDate(email.getDat(i));
	singlemail.setNumber(email.getNum(i));
	singlemail.setContent(email.getCon(i));
	mails.add(singlemail);
}
	return "mailbox.xhtml";

}

public void  display(DataBean d)
{
	System.out.println("the content in c is:"+d.content);
	this.c=d;
}

public void delete() 
	{
		
	Iterator<DataBean> itr=mails.iterator();
    	while(itr.hasNext())
        {
      	DataBean data = (DataBean)itr.next();
      	if(data.b)
        {
        int i = (data.number)-1;
	email.remove(i);
	
	}
	}
	}

public String send()
{
	email.sendMessage(touser, userid, tosubject, tocontent);
	return "welcome.xhtml";
}

public void logout()
{
	email.disconnect();
}
}

