package com.web.cobra.k8s.crd.client;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

public class KubeUtil {

	static Logger log = LoggerFactory.getLogger(KubeUtil.class);

	private static final String API_SRV_ADDRESS = "http://127.0.0.1:12000";

	public static DefaultKubernetesClient getClient() {
		Config config = new ConfigBuilder().withMasterUrl(API_SRV_ADDRESS).build();
		// 使用DefaultKubernetesClient默认的就足够了
		DefaultKubernetesClient client = new DefaultKubernetesClient(config);
		URL masterUrl = client.getMasterUrl();
		log.info("connect k8s success,masterUrl->" + masterUrl.toString());
		return client;
	}
}
