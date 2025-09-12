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

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 */
@RestController
@RequestMapping("/movie")
public class ChatController {

	private static final String DEFAULT_PROMPT = "你是一个演员，请列出你所参演的电影";

	private final ChatClient dashScopeChatClient;

	// 也可以使用如下的方式注入 ChatClient
	 public ChatController(ChatClient.Builder chatClientBuilder) {
	  	this.dashScopeChatClient = chatClientBuilder
				.defaultSystem(DEFAULT_PROMPT)
				 .build();
	 }

	 @GetMapping("/chat")
	 public String chat(@RequestParam(value = "input", defaultValue = "周星驰") String input) {
		 return dashScopeChatClient.prompt()
				 .user(input)
				 .call()
				 .content();
	 }



}
