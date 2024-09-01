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

export type ValidatorOptions = { checkReservedKeywords?: boolean };

export default class Validator {
  objectType: any;
  fieldsToCheck: any;

  constructor(objectType, fieldsToCheck) {
    this.objectType = objectType;
    this.fieldsToCheck = fieldsToCheck;
  }

  validate(object, _options?: ValidatorOptions) {
    if (!object) {
      throw new Error(`No ${this.objectType}.`);
    }
    checkForAbsentAttributes(this, object);
  }
}

function checkForAbsentAttributes(validator, object) {
  const absentAttributes: any[] = [];
  validator.fieldsToCheck.forEach(attribute => {
    if (!object[attribute]) {
      absentAttributes.push(attribute);
    }
  });
  if (absentAttributes.length !== 0) {
    const plural = absentAttributes.length > 1;
    throw new Error(
      `The ${validator.objectType} attribute${plural ? 's' : ''} ${absentAttributes.join(', ')} ${plural ? 'were not' : 'was not'} found.`,
    );
  }
}
