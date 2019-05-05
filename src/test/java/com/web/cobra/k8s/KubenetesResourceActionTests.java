package com.web.cobra.k8s;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.web.cobra.k8s.crd.ResourceAction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KubenetesResourceActionTests {

	@Autowired
	private ResourceAction resourceAction;

	@Test
	public void testcreateResourceByYaml() throws Exception {
		Map<String, Object> createResource = resourceAction.createResourceByYaml();
		System.out.println("resource:"+createResource);
	}
	
	
	@Test
	public void testcreateResourceJson() throws Exception {
		Map<String, Object> createResource = resourceAction.createResourceByJsonStr();
		System.out.println("resource:"+createResource);
	}
	
	@Test
	public void testListResource() {
		Map<String, Object> findResource = resourceAction.listResource();
		System.out.println("findResource:"+findResource);
	}
	
	@Test
	public void tesgetResourceByResourceName() {
		Map<String, Object> findResource = resourceAction.getResourceByResourceName();
		System.out.println("findResource:"+findResource);
	}
	
	@Test
	public void testgetResourceByLabels() {
		Map<String, Object> resourceByLabels = resourceAction.getResourceByLabels();
		System.out.println("resourceByLabels:"+resourceByLabels);
	}
	
	@Test
	public void testUpdateResource() throws Exception {
		Map<String, Object> updateResource = resourceAction.updateResource();
		System.out.println("updateResource:"+updateResource);
	}
	
	@Test
	public void testDeleteResource() {
		Map<String, Object> deleteResource = resourceAction.deleteResource();
		System.out.println("deleteResource:"+deleteResource);
	}
}
