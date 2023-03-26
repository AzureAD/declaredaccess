/// <reference types="react" />
export type SignInProps = {
    buttonText?: string;
    buttonClassName?: string;
    messageText?: string;
    messageClassName?: string;
};
export declare function SignIn({ buttonText, buttonClassName, messageClassName, messageText }: SignInProps): JSX.Element;
export default SignIn;
