/**
 * Copyright 2013-2024 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import createJDLApplication from '../../models/jdl-application-factory.js';
import { convertOptions } from './option-converter.js';
import ApplicationOptions from '../../jhipster/application-options.js';
import JDLUnaryOption from '../../models/jdl-unary-option.js';
import JDLBinaryOption from '../../models/jdl-binary-option.js';
import { ParsedJDLApplication } from './types.js';
import AbstractJDLOption from '../../models/abstract-jdl-option.js';
import JDLApplication from '../../models/jdl-application.js';

const {
  OptionNames: { BASE_NAME },
} = ApplicationOptions;

export default { convertApplications };

/**
 * Converts parsed applications to JDL applications.
 * @param {Array<Object>} parsedApplications - the parsed applications.
 * @return {Array} the converted JDL applications.
 */
export function convertApplications(parsedApplications: ParsedJDLApplication[]): JDLApplication[] {
  if (!parsedApplications) {
    throw new Error('Applications have to be passed so as to be converted.');
  }
  return parsedApplications.map(parsedApplication => {
    const jdlApplication = createJDLApplication(parsedApplication.config, parsedApplication.namespaceConfigs);
    jdlApplication.addEntityNames(parsedApplication.entities);
    const entityOptions = getEntityOptionsInApplication(parsedApplication);
    checkEntityNamesInOptions(jdlApplication.getConfigurationOptionValue(BASE_NAME), entityOptions, parsedApplication.entities);
    entityOptions.forEach(option => jdlApplication.addOption(option));
    return jdlApplication;
  });
}

function getEntityOptionsInApplication(parsedApplication: ParsedJDLApplication): AbstractJDLOption[] {
  return convertOptions(parsedApplication.options, parsedApplication.useOptions || []);
}

/**
 * Checks whether the entity names used in the options are present in the entity names declared for the application.
 * @param applicationName - the application's name
 * @param entityOptions - the options declared in the application
 * @param entityNamesInApplication - the entity names declared in the application
 */
function checkEntityNamesInOptions(
  applicationName: string,
  entityOptions: (JDLUnaryOption | JDLBinaryOption)[],
  entityNamesInApplication: string[] | undefined,
) {
  const entityNamesInApplicationSet = new Set<string>(entityNamesInApplication);
  entityOptions.forEach(option => {
    const entityNamesForTheOption = option.resolveEntityNames(entityNamesInApplication);
    entityNamesForTheOption.forEach(entityNameForTheOption => {
      if (!entityNamesInApplicationSet.has(entityNameForTheOption)) {
        throw new Error(
          `The entity ${entityNameForTheOption} in the ${option.name} option isn't declared in ${applicationName}'s entity list.`,
        );
      }
    });
  });
}
