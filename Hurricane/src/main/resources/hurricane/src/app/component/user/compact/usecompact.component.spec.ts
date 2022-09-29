/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCompactComponent } from './usercompact.component';

describe('UserCompactComponent', () => {
  let component: UserCompactComponent;
  let fixture: ComponentFixture<UserCompactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCompactComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCompactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
