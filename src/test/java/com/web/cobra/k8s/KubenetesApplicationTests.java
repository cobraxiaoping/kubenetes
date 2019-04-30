package com.web.cobra.k8s;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.web.cobra.k8s.crd.ResourceDefinition;

import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KubenetesApplicationTests {
	
	@Autowired
	private ResourceDefinition resourceDefinition;

	@Test
	public void testCreateResourceDefinition1() {
		CustomResourceDefinition createResourceDefinition1 = resourceDefinition.createResourceDefinition1();
		System.out.println("createResourceDefinition1:"+createResourceDefinition1.toString());
	}
	
	@Test
	public void testCreateResourceDefinition2() {
		CustomResourceDefinition createResourceDefinition2 = resourceDefinition.createResourceDefinition2();
		System.out.println("createResourceDefinition2:"+createResourceDefinition2.toString());
	}
}
