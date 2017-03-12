/**
 *  Tesla Switch for SmartThings
 *  Schwark Satyavolu
 *  Originally based on: Allan Klein's (@allanak) and Mike Maxwell's code
 *
 *  Usage:
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
	definition (name: "Tesla Car", namespace: "schwark", author: "Schwark Satyavolu") {
	capability "Switch"
	capability "Lock"
	capability "Battery"
	capability "Contact Sensor"
	capability "Presence Sensor"
	capability "Thermostat"
	capability "Polling"
	capability "Alarm"
	capability "Button" // for flash lights
	capability "Refresh"
	command "setId", ["string"]
	}

simulator {
		// TODO: define status and reply messages here
	}

tiles {
	standardTile("switch", "device.switch", width: 3, height: 2, canChangeIcon: true) {
        	state "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
        	state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
   		}
   	standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
	}

preferences {
}

    main "switch"
    details(["switch","refresh"])
}

def updated() {
}

def runCommand() {
	parent.runCommand(state.command, settings.silent, settings.nodelay)
}

def push() {
	runCommand()	
}

def refresh() {
	log.debug("running device refresh for Tesla switch")
	parent.runCommand('STATUS', false, false)
}

def on() {
	runCommand()
	sendEvent(name: "switch", value: "on")
}

def off() {
	runCommand()
	sendEvent(name: "switch", value: "off")
}

def setCommand(command) {
	state.command = command
}



