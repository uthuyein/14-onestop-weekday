import * as LucideIcons from "lucide-react";

export type IconType = keyof typeof LucideIcons

export type OptionItem = {
    key: string;
    value: string | number;
};

export type LinkItem = {
    link: string;
    name: string | number;
    icon?:IconType
};


export type Active={
    key:string,
    value:string
}