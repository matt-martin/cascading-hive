/*
* Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
*
* Project and contact information: http://www.cascading.org/
*
* This file is part of the Cascading project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package cascading.flow.hive;

import java.io.Serializable;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.session.SessionState;

/**
 * A simple factory class for creating Driver instances. This class exists to make
 * testing and stubbing within the HiveRiffle easier.
 */
public class HiveDriverFactory implements Serializable
  {

  /** a HiveConf object. */
  protected transient HiveConf hiveConf;

  /**
   * Creates a new Driver instance and sets everything up for compiling/processing queries. Users of
   * this method are responsible for destroying the driver instance, after they are done.
   *
   * @return a new Driver instance.
   */
  public Driver createHiveDriver( )
    {
    SessionState.start( getHiveConf() );
    SessionState.get().setIsSilent( true );
    Driver driver = new Driver( getHiveConf() );
    driver.init();
    return driver;
    }

  /**
   * Private helper method to return the HiveConf object.
   * @return the HiveConf object.
   */
  private HiveConf getHiveConf()
    {
    if ( this.hiveConf == null )
      this.hiveConf = new HiveConf();
    return this.hiveConf;
    }

  }
