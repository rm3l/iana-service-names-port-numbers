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
package org.rm3l.servicenamesportnumbers.app.configuration.graphql

import graphql.kickstart.tools.SchemaParser
import graphql.schema.GraphQLSchema
import graphql.Scalars
import org.rm3l.servicenamesportnumbers.ServiceNamesPortNumbersClient
import org.rm3l.servicenamesportnumbers.app.resolvers.Query
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.File

@Configuration
class GraphQLConfiguration(val registryClient: ServiceNamesPortNumbersClient) {

    @Bean
    fun graphQLSchema(): GraphQLSchema {
        val allSchemas = PathMatchingResourcePatternResolver()
                .getResources("/schema/**/*.graphqls")
                .map { "schema${File.separator}${it.filename}" }
                .toList()
        return SchemaParser.newParser()
                .files(*allSchemas.toTypedArray())
		.scalars(Scalars.GraphQLLong)
                .resolvers(Query(registryClient))
                .build()
                .makeExecutableSchema()
    }

}
