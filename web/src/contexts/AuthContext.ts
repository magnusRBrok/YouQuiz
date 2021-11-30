import React from "react";
import { AuthStore } from "../stores/authStore";

interface IStoreContext {
  authStore: AuthStore;
}

const authStore = new AuthStore();

export const StoreContext = React.createContext<IStoreContext>({
  authStore,
});
