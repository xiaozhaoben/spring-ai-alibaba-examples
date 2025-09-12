/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.example.helloworld;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 */
@RestController
@RequestMapping("/ai")
public class AIController {

	private static final String DEFAULT_PROMPT = "你是一个聊天机器人，在聊天的时候使用{voice}的语气";

	private final ChatClient dashScopeChatClient;

	// 也可以使用如下的方式注入 ChatClient
	 public AIController(ChatClient.Builder chatClientBuilder) {
	  	this.dashScopeChatClient = chatClientBuilder
				.defaultSystem(DEFAULT_PROMPT)
				 .build();
	 }

	 @GetMapping("/chat")
	 public String chat(@RequestParam(value = "input", defaultValue = "讲一个笑话") String input, String voice) {
		 return dashScopeChatClient.prompt()
				 .system(sp -> sp.param("voice", voice))
				 .user(input)
				 .call()
				 .content();
	 }



}
