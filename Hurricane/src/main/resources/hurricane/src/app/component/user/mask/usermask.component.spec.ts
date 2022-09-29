/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMaskComponent } from './usermask.component';

describe('UserMaskComponent', () => {
  let component: UserMaskComponent;
  let fixture: ComponentFixture<UserMaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserMaskComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserMaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
