/*
 * JCommons
 * Copyright (C) 2018  姜永春
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jiangyc.jcommons.swing.event;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Action manager class.Load all Action scanners and use them to scan all the actions in the project and load them
 * into a specific collection.
 *
 * It also provides some methods for manipulating these loaded Actions.
 */
public class ActionManager {
    /**
     * Store all Actions registered to the project.
     */
    private Map<String, Action> actionMap;

    /**
     * Initialize the Action Manager according to the given Action set.
     * @param actionMap Initializes the Action collection used by the Action Manager.
     */
    protected ActionManager(Map<String, Action> actionMap) {
        this.actionMap = Objects.requireNonNull(actionMap);
    }

    /**
     * A static method that uses the Action scanner registered in the project to load the Action and use it to
     * initialize the Action Manager.
     * @return Action manager initialized according to the Action scanner.
     */
    public static ActionManager getInstance() {
        ServiceLoader<ActionScanner> actionScanners = ServiceLoader.load(ActionScanner.class);
        Map<String, Action> actionMap = new HashMap<>();

        for (ActionScanner actionScanner : actionScanners) {
            for (Action action : actionScanner.obtain()) {
                actionMap.put(action.getId(), action);
            }
        }

        return new ActionManager(actionMap);
    }

    /**
     * According to Action ID to get Action.
     * @param id Action ID to get
     * @return Action registered to the project
     */
    public Action getAction(String id) {
        return actionMap.get(id);
    }

    /**
     * Get the child Action for the given ID.
     * @param id Action ID to get
     * @return Child Action Set of Given ID
     */
    public List<Action> getSubActions(String id) {
        List<Action> actions = actionMap.values().stream()
                .filter((action -> id == action.getParent() || id.equals(action.getParent())))
                .collect(Collectors.toList());

        Collections.sort(actions);

        return actions;
    }
}
