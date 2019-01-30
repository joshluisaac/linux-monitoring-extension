# AppDynamics Linux Monitoring Extension

This extension works only with the standalone machine agent. It has been tested against Linux 2.6.32 on Ubuntu; info to be updated as tests against other distributions and Linux versions are completed.

# Use Case

The Linux monitoring extension gathers metrics for a Linux machine and sends them to the AppDynamics Metric Browser.

## Pre-requisites
Before the extension is installed, the prerequisites mentioned [here](https://community.appdynamics.com/t5/Knowledge-Base/Extensions-Prerequisites-Guide/ta-p/35213) need to be met. Please do not proceed with the extension installation if the specified prerequisites are not met.

## Installation

1. To build from source, clone this repository and run 'mvn clean install'. This will produce a LinuxMonitor-VERSION.zip in the target directory. Alternatively, download the latest release archive from [Github](https://github.com/Appdynamics/linux-monitoring-extension/releases)
2. Unzip LinuxMonitor.zip and copy the 'LinuxMonitor' directory to `<MACHINE_AGENT_HOME>/monitors/`
3. Configure the extension by referring to the below section.
4. Restart the Machine Agent. 
 
In the AppDynamics Metric Browser, look for: Application Infrastructure Performance  | \<Tier\> | Custom Metrics | Linux (or the custom path you specified).


## Configuration

Note : Please make sure not to use tab (\t) while editing yaml files. You can validate the yaml file using a [yaml validator](http://yamllint.com/)

1. Configure the Linux Extension by editing the config.yml file in `<MACHINE_AGENT_HOME>/monitors/LinuxMonitor/`.
2. The metricPrefix of the extension has to be configured as specified [here](https://community.appdynamics.com/t5/Knowledge-Base/How-do-I-troubleshoot-missing-custom-metrics-or-extensions/ta-p/28695#Configuring%20an%20Extension). Please make sure that the right metricPrefix is chosen based on your machine agent deployment, otherwise this could lead to metrics not being visible in the controller.
3. Configure the path to the config.yml file by editing the <task-arguments> in the monitor.xml file in the `<MACHINE_AGENT_HOME>/monitors/LinuxMonitor/` directory. Below is the sample

     ```
     <task-arguments>
         <!-- config file-->
         <argument name="config-file" is-required="true" default-value="monitors/LinuxMonitor/config.yml" />
          ....
     </task-arguments>
    ```
4. filters: Filters for disk, CPU and mountedNFS can be configured in config.yml to fetch data only for particular entities. Be default, all entities are set to report.
5. All metrics to be reported are configured in metrics.xml. Users can remove entries from metrics.xml to stop the metric from reporting.


## Metrics
### Metric Category: CPU

|Metric Name            	|Description|
|------------------------------	|------------|
|nice                   | niced processes executing in user mode
|iowait					| waiting for I/O to complete
|system					| processes executing in kernel mode
|idle                   | twiddling thumbs
|irq                    | servicing interrupts
|softirq                | servicing softirqs
|CPU cores (logical)    | Number of CPU cores

### Metric Category: disk

|Metric Name            	|Description|
|------------------------------	|------------|
|I/Os currently in progress		|Current I/O operations	
|reads completed successfully 	|Number of successfull reads
|reads merged					|Reads merged
|sectord read 					|Sectors read
|sectors written 				|Sectors written 
|time spent doing I/Os(ms) 		|time in ms spent doing I/Os 
|time spent reading(ms) 		|time in ms spent reading 
|time spent writing(ms)			|time in ms spent writing 		 
|writes completed 				|Number of writes completed 
|writes merged 					|Number of writes merged
|Avg I/O Utilization %          |Percentage of CPU time during which I/O requests were issued to the device

### Metric Category: disk usage

|Metric Name            	|Description|
|------------------------------	|------------|
|size (MB)	                    |	
|used (MB) 	                    |
|available (MB)					|
|use %       					|

### Metric Category: file

|Metric Name            	|Description|
|------------------------------	|------------|
|agelimit						|Age limit
|fhalloc						|fhalloc
|fhfree 						|fhfree
|unused							|unused

### Metric Category: load average

|Metric Name            	|Description|
|------------------------------	|------------|
|load avg (1 min) 				|Load average 1 Minute
|load avg (5 min) 				|Load average 5 Minute
|load avg (15 min) 				|Load average 15 Minute

### Metric Category: memory

|Metric Name            	|Description|
|------------------------------	|------------|
|free                           |Free Memory
|buffers 						|Buffers 
|cached 						|Cached
|swapCached                     |Swap Cached
|commit limit 					|Commit limit 
|dirty 							|Dirty
|active 						|Active
|inactive 						|Inactive
|swapTotal                      |Swap Total
|swapFree                       |Swap Free
|writeback                      |WriteBack
|mapped 						|Mapped
|slab 						    |Memory Slab
|commited_as					|Committed As

### Metric Category: network

|Metric Name            	|Description|
|------------------------------	|------------|
|receive bytes 					|Receive bytes
|receive compressed 			|Receive compressed
|receive drop 					|Receive drop
|receive errs 					|Receive errors
|receive packets 				|Receive packets
|transmit bytes 				|Transmit bytes
|transmit compressed 			|Transmit compresses
|transmit drop 					|Transmit drop
|transmit errs 					|Transmit errors
|transmit packets 				|Transmit packets

### Metric Category: page

|Metric Name            	|Description|
|------------------------------	|------------|
|page fault 					|Page fault
|page in 						|Page in
|page out 						|Page out
|swap page in					|Swap page in
|swap page out					|Swap page out

### Metric Category: process

|Metric Name            	|Description|
|------------------------------	|------------|
|blocked 						|Blocked processes
|count 							|Total number of processes
|processes 						|Nnumber of processes
|running 						|Running processes
|runqueue 						|Processes in run queue

### Metric Category: socket

|Metric Name            	|Description|
|------------------------------	|------------|
|raw 							|Raw sockets
|tcp 							|TCP sockets
|udp 							|UDP sockets
|used 							|Used sockets

### Metric Category: mountedNFSStatus
An availability status for any external network file system (NFS) mounts is reported by executing the command `df | grep <fileSystem> | wc -l`.
|------------------------------	|------------|
|1K-blocks 							|Number of 1-K blocks
|used (MB) 							|Used space
|available (MB) 					|Available space
|used %							    |Percentage of space used


### Metric Category: nfsIOStats
The storage metrics for any external network file system (NFS) mounts is reported by executing the command `iostat -d <fileSystem>`. Following metrics are reported:

|Metric Name            	|Description|
|------------------------------	|------------|
|rkB_nor/s 						|The number of blocks read by applications using the NFS mount
|WkB_nor/s                      |The number of blocks written by applications using the NFS mount
|rkB_dir/s 		                |The number of blocks read from files opened with the O_DIRECT flag
|WkB_dir/s                      |The number of blocks written from files opened with the O_DIRECT flag
|rkB_svr/s 						|The number blocks read from the NFS server by the NFS client
|WkB_svr/s                      |The number blocks written from the NFS server by the NFS client
|ops/s                          |The number of operations per second issued to the filesystem.
|rops/s                         |The number of read operations per second issued to the filesystem.
|wops/s                         |The number of write operations per second issued to the filesystem.


Note : By default, a Machine agent or a AppServer agent can send a fixed number of metrics to the controller. To change this limit, please follow the instructions mentioned [here](http://docs.appdynamics.com/display/PRO14S/Metrics+Limits).
For eg.
```
    java -Dappdynamics.agent.maxMetrics=2500 -jar machineagent.jar
```

## Workbench

Workbench is a feature by which you can preview the metrics before registering it with the controller. This is useful if you want to fine tune the configurations. Workbench is embedded into the extension jar.
To use the workbench
Follow all the installation steps
Start the workbench with the command
      java -jar /monitors/LinuxMonitor/linux-monitoring-extension.jar
   

This starts an http server at http://host:9090/. This can be accessed from the browser.
If the server is not accessible from outside/browser, you can use the following end points to see the list of registered metrics and errors.
#Get the metricStats
    curl http://localhost:9090/api/metricStats
    #Get the registered metrics
    curl http://localhost:9090/api/metric-paths
You can make the changes to config.yml and validate it from the browser or the API
Once the configuration is complete, you can kill the workbench and start the Machine Agent.


## Contributing

Always feel free to fork and contribute any changes directly here on GitHub.

## Community

Find out more in the [AppSphere] (http://www.appdynamics.com/community/exchange/extension/linux-monitoring-extension) community.

## Support

For any questions or feature request, please contact [AppDynamics Support](mailto:help@appdynamics.com).


