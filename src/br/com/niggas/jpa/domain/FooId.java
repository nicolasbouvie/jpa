package br.com.niggas.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FooId implements Serializable {
	private static final long serialVersionUID = -3840132988729781728L;
	
	@Column
	private Long foo;
	@Column(name="bar_id")
	private Long barId;

	public FooId() {
	}
	public FooId(Long foo, Long barId) {
		this.foo = foo;
		this.barId = barId;
	}
	public Long getFoo() {
		return foo;
	}
	public void setFoo(Long foo) {
		this.foo = foo;
	}
	public Long getBarId() {
		return barId;
	}
	public void setBarId(Long barId) {
		this.barId = barId;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
