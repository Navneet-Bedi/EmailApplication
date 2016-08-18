/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc9959;

import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author NAVNEET
 */
@Named(value = "dataBean")
@Dependent
public class DataBean {

    /**
     * Creates a new instance of DataBean
     */
    boolean b;
    String from;
    String subject;
    Date date;
    int number;
    String content;

   public DataBean()
	{
	b=false;
	from=null;
	subject=null;
	date=null;
	number=0;
	content=null;
	}

    DataBean(boolean b, String from, String subject, Date date, int number, String content)
    {
      this.b = b;
      this.from = from;
      this.subject = subject;
      this.date = date;
      this.number = number;
      this.content=content;
    }
   
    public boolean getB() {return b;}
    public void setB(boolean b) {this.b =b;}
    public String getFrom() {return from;}
    public void setFrom(String from) {this.from = from;}
    public String getSubject() {return subject;}
    public void setSubject(String subject) {this.subject = subject;}
    public Date  getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
}
