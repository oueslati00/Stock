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

/* eslint-disable no-new, no-unused-expressions */
import { it, describe } from 'esmocha';
import { expect } from 'chai';
import { relationshipTypes } from '../jhipster/index.js';
import { relationshipTypeExists } from './relationship-types.js';

describe('jdl - RelationshipTypes', () => {
  describe('exists', () => {
    describe('when checking for a valid unary relationship type', () => {
      it('should return true', () => {
        expect(relationshipTypeExists(relationshipTypes.MANY_TO_ONE)).to.be.true;
      });
    });
    describe('when checking for an invalid relationship type', () => {
      it('should return false', () => {
        // @ts-expect-error
        expect(relationshipTypeExists('NOTHING')).to.be.false;
      });
    });
  });
});
