package br.com.niggas.jpa.domain;
import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.niggas.jpa.EMUtil;
import br.com.niggas.jpa.domain.Bar;
import br.com.niggas.jpa.domain.Foo;
import br.com.niggas.jpa.domain.FooId;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompositeKeyTest {
	private static EntityManager em;
	
	@BeforeClass
	public static void setupClass() {
		em = EMUtil.getEntityManager();
	}
	
	@Test
	public void test1_insertFoo() {
		Assert.assertTrue(EMUtil.doTransaction(new Runnable() {
			@Override
			public void run() {
				Bar b = new Bar();
				b.setId(1L);
				b.setName("Bar");
				
				
				Foo f = new Foo();
				f.setId(new FooId(1L, 1L));
				f.setName("Foo");
				f.setBar(b);

				b.setFoos(Arrays.asList(f));
				em.persist(b);
				em.persist(f);
			}
		}, true));
	}
	
	@Test
	public void test2_findBar() {
		Bar bar = em.find(Bar.class, 1L);
		Assert.assertEquals("Bar", bar.getName());
	}

	@Test
	public void test3_findFoo() {
		Foo foo = em.find(Foo.class, new FooId(1L, 1L));
		Assert.assertEquals("Foo", foo.getName());
	}
	
	@Test
	public void test4_findFoosFromBar() {
		Bar bar = em.find(Bar.class, 1L);
		Assert.assertEquals(1, bar.getFoos().size());
	}
}
