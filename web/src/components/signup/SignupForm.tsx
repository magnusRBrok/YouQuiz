import { Button, IconButton } from "@chakra-ui/button";
import {
  FormControl,
  FormErrorMessage,
  FormLabel,
} from "@chakra-ui/form-control";
import { Input, InputGroup, InputRightElement } from "@chakra-ui/input";
import { Flex, Stack } from "@chakra-ui/layout";
import React, { FC, useRef, useState } from "react";
import { useForm } from "react-hook-form";
import { HiEye, HiEyeOff } from "react-icons/hi";

export const SignupForm: FC = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const onSubmit = (data: any) => {
    const { email, password, confirmedPassword } = data;
    console.log(email, password, confirmedPassword);
    //TODO call login endpoint
  };

  const [revealPassword, setRevealPassword] = useState(false);
  const password = useRef({});
  password.current = watch("password", "");

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
        <FormControl isInvalid={errors.confirmedPassword}>
          <Flex justify="space-between">
            <FormLabel htmlFor="password">Confirm password</FormLabel>
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
              id="confirmed-password"
              type={revealPassword ? "text" : "password"}
              autoComplete="current-password"
              {...register("confirmedPassword", {
                validate: (value) =>
                  value === password.current || "The passwords do not match",
              })}
            />
          </InputGroup>
          <FormErrorMessage>
            {errors.confirmedPassword && errors.confirmedPassword.message}
          </FormErrorMessage>
        </FormControl>
        <Button type="submit" colorScheme="blue" size="lg" fontSize="md">
          Sign up
        </Button>
      </Stack>
    </form>
  );
};
