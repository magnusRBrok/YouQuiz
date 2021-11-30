import { Button, IconButton } from "@chakra-ui/button";
import {
  FormControl,
  FormErrorMessage,
  FormLabel,
} from "@chakra-ui/form-control";
import { Input, InputGroup, InputRightElement } from "@chakra-ui/input";
import { Flex, Stack } from "@chakra-ui/layout";
import { FC, useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { HiEye, HiEyeOff } from "react-icons/hi";
import { StoreContext } from "../../contexts/AuthContext";

const LoginForm: FC = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const { authStore } = useContext(StoreContext);

  const onSubmit = (data: any) => {
    const { email, password } = data;

    authStore.login(email, password);
  };

  const [revealPassword, setRevealPassword] = useState(false);

  const onClickReveal = () => {
    setRevealPassword((revealPassword) => !revealPassword);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Stack spacing="6">
        <FormControl isInvalid={errors.email}>
          <FormLabel htmlFor="email">Email address</FormLabel>
          <Input
            id="email"
            type="email"
            autoComplete="email"
            {...register("email", { required: true })}
          />
          <FormErrorMessage>
            {errors.email && errors.email.message}
          </FormErrorMessage>
        </FormControl>
        <FormControl isInvalid={errors.password}>
          <Flex justify="space-between">
            <FormLabel htmlFor="password">Password</FormLabel>
            {/* <Box
              as="a"
              color={mode("blue.600", "blue.200")}
              fontWeight="semibold"
              fontSize="sm"
            >
              Forgot Password?
            </Box> */}
          </Flex>
          <InputGroup>
            <InputRightElement>
              <IconButton
                bg="transparent !important"
                variant="ghost"
                aria-label={
                  revealPassword ? "Mask password" : "Reveal password"
                }
                icon={revealPassword ? <HiEyeOff /> : <HiEye />}
                onClick={onClickReveal}
              />
            </InputRightElement>
            <Input
              id="password"
              type={revealPassword ? "text" : "password"}
              autoComplete="current-password"
              {...register("password", {
                required: true,
              })}
            />
          </InputGroup>
          <FormErrorMessage>
            {errors.password && errors.password.message}
          </FormErrorMessage>
        </FormControl>
        <Button type="submit" colorScheme="blue" size="lg" fontSize="md">
          Sign in
        </Button>
      </Stack>
    </form>
  );
};

export default LoginForm;
