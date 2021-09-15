package org.demo;

import org.sfvl.doctesting.junitextension.HtmlPageExtension;

/**
 * Customize page that will be converted in HTML.
 *
 * We remove footer to avoid there is a modification in HTML because of the displayed date.
 * It's just a choice because HTML file is under source control and we prefered it not change executing tests.
 */
class DemoPageExtension extends HtmlPageExtension {
    @Override
    public String content(Class<?> clazz) {
        return String.join("\n",
                ":nofooter:",
                super.content(clazz)
        );
    }
}
