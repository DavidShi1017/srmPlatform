package com.jingtong.platform.message.dao;

import java.util.List;

import com.jingtong.platform.message.pojo.Message;



/**
 * ����
 * @author lenovo
 *
 */
public interface IMessageDao {
	
	public List<Message> searchMessage(Message c);
	
	public long saveMessage(Message c);

	public int updateMessage(Message c);
	
}
