import { Menu, MenuButton, MenuList, MenuItem } from "@chakra-ui/react";
import { FC, useContext } from "react";
import { IconButton } from "@chakra-ui/button";
import { HamburgerIcon } from "@chakra-ui/icons";
import { Link } from "react-router-dom";
import { StoreContext } from "../../contexts/AuthContext";
import { observer } from "mobx-react-lite";

const NavbarMenu: FC = () => {
  const { authStore } = useContext(StoreContext);
  const isAuthenticated = authStore.isAuthenticated();

  return (
    <Menu>
      <MenuButton icon={<HamburgerIcon />} as={IconButton} variant="outline" />
      <MenuList>
        {!isAuthenticated && (
          <Link to="/login">
            <MenuItem>
              <>Login</>
            </MenuItem>
          </Link>
        )}
        {isAuthenticated && (
          <Link to="/">
            <MenuItem onClick={() => authStore.logout()}>
              <>Logout</>
            </MenuItem>
          </Link>
        )}
      </MenuList>
    </Menu>
  );
};

export default observer(NavbarMenu);
