//
//
//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.navigation.ChooseByNameContributor;
//import com.intellij.navigation.NavigationItem;
//import com.intellij.openapi.project.Project;
//import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class X8lChooseByNameContributor implements ChooseByNameContributor {
//    @NotNull
//    @Override
//    public String[] getNames(Project project, boolean includeNonProjectItems) {
//        List<X8lProperty> properties = X8lUtil.findProperties(project);
//        List<String> names = new ArrayList<String>(properties.size());
//        for (X8lProperty property : properties) {
//            if (property.getKey() != null && property.getKey().length() > 0) {
//                names.add(property.getKey());
//            }
//        }
//        return names.toArray(new String[names.size()]);
//    }
//
//    @NotNull
//    @Override
//    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
//        // TODO: include non project items
//        List<X8lProperty> properties = X8lUtil.findProperties(project, name);
//        return properties.toArray(new NavigationItem[properties.size()]);
//    }
//}