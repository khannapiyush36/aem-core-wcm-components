/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.adobe.cq.wcm.core.components.sandbox.models;

import java.util.Calendar;
import java.util.Map;

import com.adobe.cq.export.json.ComponentExporter;
import org.junit.Test;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PageTest {

    private Page underTest = new MockPage();

    @Test(expected = UnsupportedOperationException.class)
    public void testGetFavicons() throws Exception {
        underTest.getFavicons();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetFaviconClientLibPath() throws Exception {
        underTest.getAppResourcesPath();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetRedirectTarget() throws Exception {
        underTest.getRedirectTarget();
    }

    private static class MockPage implements Page {
        @Override
        public String getLanguage() {
            return null;
        }

        @Override
        public Calendar getLastModifiedDate() {
            return null;
        }

        @Override
        public String[] getKeywords() {
            return new String[0];
        }

        @Override
        public String getDesignPath() {
            return null;
        }

        @Override
        public String getStaticDesignPath() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String[] getClientLibCategories() {
            return new String[0];
        }

        @Override
        public String getTemplateName() {
            return null;
        }

        @Nonnull
        @Override
        public Map<String, ? extends ComponentExporter> getExportedItems() {
            return null;
        }

        @Nonnull
        @Override
        public String[] getExportedItemsOrder() {
            return new String[0];
        }

        @Nonnull
        @Override
        public String getExportedType() {
            return null;
        }
    }
}