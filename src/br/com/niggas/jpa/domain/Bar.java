package br.com.niggas.jpa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bar
 *
 */
@Entity
public class Bar implements Serializable {
	private static final long serialVersionUID = -3320242601618941346L;
	
	@Id
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="bar", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Foo> foos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Foo> getFoos() {
		return foos;
	}

	public void setFoos(List<Foo> foos) {
		this.foos = foos;
	}
}
