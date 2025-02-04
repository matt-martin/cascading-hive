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

package cascading.tap.hive;

import java.io.IOException;

import cascading.flow.FlowProcess;
import cascading.scheme.NullScheme;
import cascading.tap.Tap;
import cascading.tap.hive.HiveNullTap;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

@SuppressWarnings( "unchecked" )
public class HiveNullTapTest
  {
  @Test
  public void testNullTap() throws IOException
    {
    assertEquals( "/dev/null", HiveNullTap.DEV_NULL.getIdentifier() );
    assertEquals( "/dev/zero", HiveNullTap.DEV_ZERO.getIdentifier() );

    Tap[] nullTaps = new Tap[]{HiveNullTap.DEV_ZERO, HiveNullTap.DEV_NULL};
    for( Tap tap : nullTaps )
      {
      assertEquals( new NullScheme(), tap.getScheme() );
      assertNull( tap.openForRead( Mockito.mock( FlowProcess.class ), new Object() ) );
      assertNull( tap.openForWrite( Mockito.mock( FlowProcess.class ), new Object() ) );
      assertTrue( tap.createResource( Mockito.mock( FlowProcess.class ) ) );
      assertFalse( tap.deleteResource( Mockito.mock( FlowProcess.class ) ) );
      }

    }

  }
