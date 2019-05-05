package com.web.cobra.k8s;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import com.web.cobra.k8s.crd.ResourceDefinition;

import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KubenetesResourceDefinitionTests {

	@Autowired
	private ResourceDefinition resourceDefinition;

	@Test
	public void testCreateResourceDefinition1() {
		CustomResourceDefinition createResourceDefinition1 = resourceDefinition.createResourceDefinition1();
		System.out.println("createResourceDefinition1:" + createResourceDefinition1.toString());
	}

	@Test
	public void testCreateResourceDefinition2() {
		CustomResourceDefinition createResourceDefinition2 = resourceDefinition.createResourceDefinition2();
		System.out.println("createResourceDefinition2:" + createResourceDefinition2.toString());
	}

	@Test
	public void testFindAllResourceDefinition() {
		CustomResourceDefinitionList resourceDefinitionList = resourceDefinition.findAllResourceDefinition();
		System.out.println("resourceDefinitionList:" + resourceDefinitionList.toString());
		if (!ObjectUtils.isEmpty(resourceDefinitionList)) {
			List<CustomResourceDefinition> items = resourceDefinitionList.getItems();
			items.forEach(rd -> {
				System.out.println(rd.toString());
			});
		}
	}

	@Test
	public void testFindResourceDefinitionByCondition() {
		CustomResourceDefinition customResourceDefinition = resourceDefinition.findResourceDefinitionByCondition();
		System.out.println("customResourceDefinition:" + customResourceDefinition.toString());
	}
	
	@Test
	public void testUpdateResourceDefinition() {
		CustomResourceDefinition updateResourceDefinition = resourceDefinition.updateResourceDefinition();
		System.out.println("updateResourceDefinition:" + updateResourceDefinition.toString());
	}
	
	@Test
	public void testDeleteResourceDefinition() {
		Boolean deleteResourceDefinition = resourceDefinition.deleteResourceDefinition();
		System.out.println("deleteResourceDefinition:"+deleteResourceDefinition);
	}
}
