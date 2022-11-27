import { Component, Input, OnInit } from '@angular/core';
import ClientComponentBase from "../base/ClientComponentBase";
import { ClientService } from "../../../service/client/client.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.scss']
})
export class ClientEditComponent extends ClientComponentBase implements OnInit {
  id: number | null;

  constructor(private route: ActivatedRoute) {
    super();
    this.id = null;
    this.route.params.subscribe(params => {
      this.id = params["id"];
    });
  }

  ngOnInit(): void {
  }
}
