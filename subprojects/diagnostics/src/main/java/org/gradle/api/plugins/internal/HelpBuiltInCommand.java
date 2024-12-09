/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.plugins.internal;

import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.configuration.project.BuiltInCommand;
import org.gradle.internal.service.scopes.Scope;
import org.gradle.internal.service.scopes.ServiceScope;

import java.util.Collections;
import java.util.List;

@ServiceScope(Scope.Global.class)
public class HelpBuiltInCommand implements BuiltInCommand {
    @Override
    public List<String> asDefaultTask() {
        return Collections.singletonList(ProjectInternal.HELP_TASK);
    }

    @Override
    public boolean commandLineMatches(List<String> taskNames) {
        return taskNames.isEmpty() || taskNames.get(0).equals(ProjectInternal.HELP_TASK) || taskNames.get(0).equals(":" + ProjectInternal.HELP_TASK);
    }
}
