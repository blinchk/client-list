import { Component, OnInit } from '@angular/core';
import ClientComponentBase from "../base/ClientComponentBase";
import { ClientService } from "../../../service/client/client.service";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.scss']
})
export class ClientAddComponent extends ClientComponentBase implements OnInit {

  constructor() {
    super();
  }

  ngOnInit(): void {
  }

}
