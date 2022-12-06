import { ClientAction } from "src/app/model/client-action";
import Client from "../../../model/client";

export default class ClientComponentBase {
  client: Client = {
    firstName: '',
    lastName: '',
    username: '',
    address: '',
    countryId: null,
  };

  public get ClientAction() {
    return ClientAction;
  }
}
