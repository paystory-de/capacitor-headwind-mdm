import type { HeadwindMDM as HeadwindMDMPlugin } from "./definitions";
import { registerPlugin } from "@capacitor/core";

const HeadwindMDM = registerPlugin<HeadwindMDMPlugin>('HeadwindMDM');

export * from './definitions';
export { HeadwindMDM };
