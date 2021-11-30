import { Menu, MenuButton, MenuList, MenuItem } from "@chakra-ui/react";
import { FC } from "react";
import { IconButton } from "@chakra-ui/button";
import { HamburgerIcon } from "@chakra-ui/icons";
import { Link } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { UserStore } from "../../stores/userStore";
import { AuthStore } from "../../stores/authStore";

const NavbarMenu: FC = () => {
  return (
    <Menu>
      {UserStore.getFirstName()}
      <MenuButton icon={<HamburgerIcon />} as={IconButton} variant="outline" />
      <MenuList>
        {!AuthStore.isAuthenticated() && (
          <Link to="/login">
            <MenuItem>
              <>Login</>
            </MenuItem>
          </Link>
        )}
        {AuthStore.isAuthenticated() && (
          <Link to="/">
            <MenuItem onClick={() => AuthStore.logout()}>
              <>Logout</>
            </MenuItem>
          </Link>
        )}
      </MenuList>
    </Menu>
  );
};

export default observer(NavbarMenu);
