import { Component, OnInit, Input} from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'alert-component',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss'],
})
export class AlertComponent implements OnInit {
  private _success = new Subject<string>();

  staticAlertClosed = false;

  @Input()
  type: string;

  @Input()
  message: string;

  constructor() {
    //this.type = "success";
    //this.message = "teste";
  }

  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);

    this._success.subscribe((message1) => this.message = message1);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.message = null);
  }
}
