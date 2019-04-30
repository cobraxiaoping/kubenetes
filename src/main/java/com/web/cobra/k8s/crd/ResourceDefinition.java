package com.web.cobra.k8s.crd;

import java.util.Collections;
import org.springframework.stereotype.Component;
import com.web.cobra.k8s.crd.client.KubeUtil;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionBuilder;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionVersionBuilder;

/**  
 * @ClassName: ResourceDefinition  
 * @Description:   主要演示了如何通过kubenetes-client 实现了k8s中资源的定义以及对资源定义的一些操作
 * @author: cobra  
 * @date: 2019年4月30日  
 * @version: v1.0
 */  
@Component
public class ResourceDefinition {

	
	/**  
	 * @Method: createResourceDefinition  
	 * @Description: 资源定义方式一：通过对象创建资源对象定义,其实现的功能也就是拼装了/crd/resource-definition.yaml
	 * @return: 
	 * @throws  
	 */  
	public CustomResourceDefinition createResourceDefinition1() {
		CustomResourceDefinition resourceDefinition = new CustomResourceDefinitionBuilder()
				.withApiVersion("apiextensions.k8s.io/v1beta1")
				.withKind("CustomResourceDefinition")
				.withNewMetadata()
				.withName("apiproducts.cobra.xp")
				.endMetadata()
				.withNewSpec()
				.withGroup("cobra.xp")
				.withVersion("v1")
				.addAllToVersions(Collections.singletonList(new CustomResourceDefinitionVersionBuilder().withName("v1")
						.withServed(true).withStorage(true).build()))
				.withScope("Cluster")
				.withNewNames()
				.withPlural("apiproducts")
				.withSingular("apiproduct")
				.withKind("ApiProduct")
				.endNames()
				.endSpec()
				.build();
		return resourceDefinition = KubeUtil.getClient().customResourceDefinitions().create(resourceDefinition);
	}
	
	/**  
	 * @Method: createResourceDefinition2  
	 * @Description:资源定义方式二：通过自定义资源yaml文件创建资源定义
	 * @return: 
	 * @throws  
	 */  
	public CustomResourceDefinition createResourceDefinition2() {
		 CustomResourceDefinition resourceDefinition = KubeUtil.getClient().customResourceDefinitions().load(getClass().getResourceAsStream("/crd/resource-definition.yaml")).get();
		 return KubeUtil.getClient().customResourceDefinitions().create(resourceDefinition);
	}
	
	
	/**  
	 * @Method: listAllResourceDefinition  
	 * @Description: 获取所有的自定义资源
	 * @param: 
	 * @return: void
	 * @throws  
	 */  
	public CustomResourceDefinitionList findAllResourceDefinition() {
		return KubeUtil.getClient().customResourceDefinitions().list();
	}
	
	
	/**  
	 * @Method: getResourceDefinition  
	 * @Description: 根据条件查找资源定义
	 * @return: void
	 * @throws  
	 */  
	public CustomResourceDefinition findResourceDefinitionByCondition() {
		return KubeUtil.getClient().customResourceDefinitions().withName("apiproducts.cobra.xp").get();
	}
	
	/**  
	 * @Method: updateResourceDefinition  
	 * @Description: 更新资源定义
	 * @param: @return
	 * @return: CustomResourceDefinition
	 * @throws  
	 */  
	public CustomResourceDefinition updateResourceDefinition() {
		 return KubeUtil.getClient().customResourceDefinitions().withName("apiproducts.cobra.xp").edit().editSpec().editOrNewNames().addNewShortName("ap").endNames().endSpec().done();
	}
	
	/**  
	 * @Method: deleteResourceDefinition  
	 * @Description: 刪除资源定义
	 * @return: Boolean
	 * @throws  
	 */  
	public Boolean deleteResourceDefinition() {
		return KubeUtil.getClient().customResourceDefinitions().withName("apiproducts.cobra.xp").delete();
	}
}
