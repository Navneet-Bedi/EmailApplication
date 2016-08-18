/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc9959;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.mail.Message;
import javax.ejb.Remote;
import java.util.*;

/**
 *
 * @author NAVNEET
 */
//@Named(value = "email")
//@Dependent
@Remote
public interface Email {
  public void sendMessage(String recipient,String sender,String subject,
       String data);
  public int connect(String login, String passwd);
  public void disconnect();
  public String getMessage(int i);
  public String getFromAddress(int i);
  public String getSub(int i);
  public Date getDat(int i);
  public String getCon(int i);
  public int  getNum(int i);
  public void remove(int i);
}
