package com.web.cobra.k8s.crd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.cobra.k8s.crd.client.KubeUtil;

import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;

/**  
 * @ClassName: ResourceAction  
 * @Description: 对资源对象的增删改查操作  
 * @author: Cobra  
 * @date: 2019年5月5日  
 * @version: v1.0
 */
@Component
public class ResourceAction {

	private static CustomResourceDefinitionContext context = null;

	static {
		context = new CustomResourceDefinitionContext.Builder().withName("animals.cobra.xp").withGroup("cobra.xp")
				.withVersion("v1").withPlural("animals").withScope("Cluster").build();
	}

	/**  
	 * @Method: createResourceByYaml  
	 * @Description: 通过yaml文件创建资源对象
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @throws  
	 */
	public Map<String, Object> createResourceByYaml() throws Exception {
		return KubeUtil.getClient().customResource(context).create(getClass().getResourceAsStream("/crd/resource-object.yaml"));
	}

	/**  
	 * @Method: createResourceByJsonStr  
	 * @Description: 通过json对象创建资源对象，其实也就是封装yaml文件的东西
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @throws  
	 */
	public Map<String, Object> createResourceByJsonStr() throws Exception {

		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("apiVersion", "cobra.xp/v1");
		rootMap.put("kind", "Animal");

		Map<String, Object> metadataMap = new HashMap<String, Object>();
		metadataMap.put("name", "dog-02");

		Map<String, Object> labelsMap = new HashMap<String, Object>();
		labelsMap.put("nickName", "dabao");
		labelsMap.put("age", "2");
		labelsMap.put("image", "");
		labelsMap.put("create-time", "2019-05-05");

		metadataMap.put("labels", labelsMap);
		rootMap.put("metadata", metadataMap);

		Map<String, Object> specMap = new HashMap<String, Object>();
		specMap.put("nickName", "dabao");
		specMap.put("age", "2");
		specMap.put("image", "");
		specMap.put("create-time", "2019-05-05");
		rootMap.put("spec", specMap);

		ObjectMapper mapper = new ObjectMapper();
		String rawJsonCustomResourceObj = mapper.writeValueAsString(rootMap);
		System.out.println("rawJsonCustomResourceObj:" + rawJsonCustomResourceObj);
		return KubeUtil.getClient().customResource(context).create(rawJsonCustomResourceObj);
	}

	/**  
	 * @Method: listResource  
	 * @Description: 查询所有资源对象
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws  
	 */
	public Map<String, Object> listResource() {
		return KubeUtil.getClient().customResource(context).list();
	}

	/**  
	 * @Method: getResource  
	 * @Description: 根据资源对象的名字查询资源对象
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws  
	 */
	public Map<String, Object> getResourceByResourceName() {
		return KubeUtil.getClient().customResource(context).get("dog-01");
	}

	/**  
	 * @Method: getResourceByLabels  
	 * @Description: 通过labels 筛选资源对象
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws  
	 */
	public Map<String, Object> getResourceByLabels() {
		Map<String, String> labelsMap = new HashMap<String, String>();
		labelsMap.put("nickName", "putong");
		labelsMap.put("age", "2");
		return KubeUtil.getClient().customResource(context).list("", labelsMap);
	}

	/**  
	 * @Method: updateResource  
	 * @Description: 更新资源对象
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<String,Object>
	 * @throws  
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> updateResource() throws Exception {
		Map<String, Object> map = KubeUtil.getClient().customResource(context).get("dog-01");
		Map<String, Object> metadata = (Map<String, Object>) map.get("metadata");
		Map<String, Object> labels = (Map<String, Object>) metadata.get("labels");
		labels.put("age", "3");
		Map<String, Object> spec = (Map<String, Object>) map.get("spec");
		spec.put("age", "3");
		return KubeUtil.getClient().customResource(context).edit("dog-01", new ObjectMapper().writeValueAsString(map));
	}

	/**  
	 * @Method: deleteResource  
	 * @Description: 删除资源对象
	 * @param: 
	 * @return: void
	 * @throws  
	 */
	public Map<String, Object> deleteResource() {
		return KubeUtil.getClient().customResource(context).delete("", "dog-02");
	}
}
