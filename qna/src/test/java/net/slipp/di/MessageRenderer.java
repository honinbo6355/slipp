package net.slipp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRenderer {
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public void renderer() {
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("di.xml");
		MessageRenderer renderer = (MessageRenderer)ac.getBean("messageRenderer");
		//MessageRenderer renderer = ac.getBean("messageRenderer", MessageRenderer.class);
		renderer.renderer();
	}
}
