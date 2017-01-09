/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 ~ Copyright 2016 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.models.form.impl.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.cq.wcm.core.components.commons.form.FormConstants;
import com.adobe.cq.wcm.core.components.models.Constants;
import com.adobe.cq.wcm.core.components.models.form.DataSourceModel;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.day.cq.wcm.foundation.forms.FormsManager;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = DataSourceModel.class,
       resourceType = FormActionTypeSettingsDataSource.RESOURCE_TYPE)
@Exporter(name = Constants.EXPORTER_NAME,
          extensions = Constants.EXPORTER_EXTENSION)
public class FormActionTypeSettingsDataSource extends DataSourceModel {

    public final static String RESOURCE_TYPE = FormConstants.RT_CORE_FORM_CONTAINER_DATASOURCE_V1 + "/actionsetting";

    @Self
    private SlingHttpServletRequest request;

    @SlingObject
    private ResourceResolver resourceResolver;

    @PostConstruct
    private void initModel() {
        SimpleDataSource actionTypeSettingsDataSource = new SimpleDataSource(getSettingsDialogs().iterator());
        initDataSource(request, actionTypeSettingsDataSource);
    }

    private List<Resource> getSettingsDialogs() {
        List<Resource> actionTypeSettingsResources = new ArrayList<>();
        FormsManager formsManager = resourceResolver.adaptTo(FormsManager.class);
        if (formsManager != null) {
            Iterator<FormsManager.ComponentDescription> actions = formsManager.getActions();
            while (actions.hasNext()) {
                FormsManager.ComponentDescription componentDescription = actions.next();
                Resource dialogResource = resourceResolver.getResource(componentDescription.getResourceType() + "/" +
                        FormConstants.NN_DIALOG);
                if (dialogResource != null) {
                    actionTypeSettingsResources.add(dialogResource);
                }
            }
        }
        return actionTypeSettingsResources;
    }
}
