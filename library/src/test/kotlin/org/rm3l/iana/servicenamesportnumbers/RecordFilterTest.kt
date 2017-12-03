/*
The MIT License (MIT)

Copyright (c) 2017 Armel Soro

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package org.rm3l.iana.servicenamesportnumbers

import org.junit.Assert
import org.junit.Test
import org.rm3l.iana.servicenamesportnumbers.domain.Protocol
import org.rm3l.iana.servicenamesportnumbers.domain.RecordFilter

class RecordFilterTest {

    @Test
    fun testEquality() {
        val filter1 = RecordFilter(listOf("service1", "service2"), listOf(Protocol.TCP), listOf(10L))
        val filter2 = RecordFilter(listOf("service1", "service2"), listOf(Protocol.TCP), listOf(10L))
        val filter3 = RecordFilter(listOf("service1", "service2"), listOf(Protocol.SCTP), listOf(10L))
        val filter4 = RecordFilter(listOf("service1"), listOf(Protocol.TCP), listOf(10L))
        Assert.assertEquals(filter1, filter2)
        Assert.assertNotEquals(filter1, filter3)
        Assert.assertNotEquals(filter1, filter4)
        Assert.assertNotEquals(filter3, filter4)
    }

}