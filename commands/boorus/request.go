/* This will send a request to rule34 api, to get a xml response with image link then redirect to discord. */
package boorus

import (
	"encoding/xml"
	"fmt"
	"io"
	"net/http"
)

// First xml field from rule 34.
type posts struct {
	XMLname xml.Name
	// Count   uint   `xml:"count,attr"`
	// Offset  uint   `xml:"offset,attr"`
	Post []post `xml:"post"`
}

// Second xml field have the attributes really needed to redirect for discord.
type post struct {
	XMLname xml.Name
	// Height         int    `xml:"height,attr"`
	// Score          int    `xml:"score,attr"`
	File_url string `xml:"file_url,attr"`
	// Parent_id      int    `xml:"parent_id,attr"`
	// Sample_url     string `xml:"sample_url,attr"`
	// Sample_width   int    `xml:"sample_width,attr"`
	// Sample_height  int    `xml:"sample_height,attr"`
	// Preview_url    string `xml:"preview_url,attr"`
	Rating string `xml:"rating,attr"`
	Tags   string `xml:"tags,attr"`
	// Id             int    `xml:"id,attr"`
	// Width          int    `xml:"width,attr"`
	// Change         int    `xml:"change,attr"`
	// Md5            string `xml:"md5,attr"`
	// Creator_id     int    `xml:"creator_id,attr"`
	// Has_children   bool   `xml:"has_children,attr"`
	// Created_at     string `xml:"created_at,attr"`
	// Status         string `xml:"status,attr"`
	// Source         string `xml:"source,attr"`
	// Has_notes      bool   `xml:"has_notes,attr"`
	// Has_comments   bool   `xml:"has_comments,attr"`
	// Preview_width  int    `xml:"preview_width,attr"`
	// Preview_height int    `xml:"preview_height,attr"`
}

func R34(tags string, i int) error {
	resp, err := http.Get("https://api.rule34.xxx/index.php?page=dapi&s=post&q=index")
	if err != nil {
		return fmt.Errorf("cannot fetch URL: %w", err)
	}

	defer resp.Body.Close()
	if resp.StatusCode != http.StatusOK {
		return fmt.Errorf("unexpected http GET status: %v", resp.Status)
	}

	msg, err := io.ReadAll(resp.Body)
	if err != nil {
		return fmt.Errorf("cannot decode xml: %w", err)
	}

	var img posts
	err = xml.Unmarshal(msg, &img)

	if err != nil {
		return fmt.Errorf("error parsing xml: %w", err)
	}
	return nil
}
