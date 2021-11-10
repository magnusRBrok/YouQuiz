import { Menu, MenuButton, MenuList, MenuItem } from "@chakra-ui/react";
import { FC } from "react";
import { IconButton } from "@chakra-ui/button";
import { HamburgerIcon } from "@chakra-ui/icons";
import { Link } from "react-router-dom";

const NavbarMenu: FC = ({ children }) => {
  return (
    <Menu>
      <MenuButton icon={<HamburgerIcon />} as={IconButton} variant="outline" />
      <MenuList>
        <MenuItem>
          <Link to="/login">Login</Link>
        </MenuItem>
      </MenuList>
    </Menu>
  );
};

export default NavbarMenu;
