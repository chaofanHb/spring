package com.cn.spring;

import org.springframework.context.ApplicationEvent;

public class MsgSendEvent extends ApplicationEvent {

	private static final long serialVersionUID = -734311622224342326L;

	private String domain;
	private String teamId;

	public MsgSendEvent(Object source, String domain, String teamId) {
		super(source);
		this.domain = domain;
		this.teamId = teamId;
	}

	public String getDomain() {
		return domain;
	}

	public String getTeamId() {
		return teamId;
	}

	@Override
	public String toString() {
		return "MsgSendEvent{" +
				"domain='" + domain + '\'' +
				", teamId='" + teamId + '\'' +
				'}';
	}
}
