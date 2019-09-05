export class ConfigModal {
    cssFooter: string;
    cssHeader: string;
    cssBody: string;
    textHeader: string;

    constructor(private footer: string, private header: string, private body: string, private txtHeader: string) {
        this.cssFooter = footer;
        this.cssHeader = header;
        this.cssBody = body;
        this.textHeader = txtHeader;
    }
}