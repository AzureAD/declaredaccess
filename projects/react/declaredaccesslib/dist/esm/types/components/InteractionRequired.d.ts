/// <reference types="react" />
export type InteractionRequiredProps = {
    buttonText?: string;
    buttonClassName?: string;
    messageText?: string;
    messageClassName?: string;
};
export declare function InteractionRequired({ buttonText, messageText, buttonClassName, messageClassName }: InteractionRequiredProps): JSX.Element;
export default InteractionRequired;
